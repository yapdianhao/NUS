### RESAMPLING 
# BOOTSTRAP METHOD
rm(list= ls())

x = c(3.39, 3.30, 2.81, 3.03, 3.44, 3.07, 3.00)
length(x)

sample(x, 7, replace = TRUE)


# LAW SCHOOL DATA

#set.seed(1)

law = read.csv("C:/Data/lawschool.csv")
law

attach(law)
cor(LSAT,GPA)

# NONPARAMETRIC BOOTSTRAP TO ESTIMATE STANDARD ERROR
R <- 1000 # number of bootstrap replicates;
n <- length(GPA) # sample size
theta.b <- numeric(R) # storage for boostrap estimates
for (b in 1:R) {
  # for each b, randomly select the indices, sampling with replacement
  i <- sample(1:n, size=n, replace=TRUE)
  LSATb <- LSAT[i] # i is a vector of indices
  GPAb <- GPA[i]
  theta.b[b] <- cor(LSATb, GPAb)
}

sd(theta.b)

#####  boot FUNCTION
library(boot)
bcor <- function(data, bindex){
  return(cor(data[bindex,1], data[bindex,2]))
}

boot.cor <- boot(law, statistic=bcor, R=1000)

# Obtain the bias and standard error
boot.cor

# https://cran.r-project.org/web/packages/boot/boot.pdf

# NONPARAMETRIC BOOTSTRAP TO ESTIMATE BIAS

theta.hat <- cor(LSAT, GPA)
R <- 1000   # can change this value
n <- nrow(law)
theta.b <- numeric(R)# storage for boostrap estimates
for (b in 1:R) {
  i <- sample(1:n, size=n, replace=TRUE)
  LSATb <- LSAT[i]
  GPAb <- GPA[i]
  theta.b[b] <- cor(LSATb, GPAb)
  }
bias <- mean(theta.b)- theta.hat
bias







############  BASIC BOOTSTRAP CI
R = 2000 # larger for estimating confidence interval
theta.b = numeric(R)
alpha = 0.05; CL = 100*(1-alpha)
for (b in 1:R) {
  i <- sample(1:n, size=n, replace=TRUE)
  LSATb <- LSAT[i]
  GPAb <- GPA[i]
  theta.b[b] <- cor(LSATb, GPAb)
}

low = quantile(theta.b, alpha/2)
high = quantile(theta.b, 1 - alpha/2)

cat("A",CL,"% confidence interval is ",
    2*theta.hat - high, 2*theta.hat - low,"\n")


############  PERCENTILE BOOTSTRAP CI
low <- quantile(theta.b, alpha/2)

high <- quantile(theta.b,1-alpha/2)

CL <- 100*(1-alpha)

cat("A",CL,"% bootstrap CI is", 
    low, high,"\n")



############  NORMAL BOOTSTRAP CI
bias = mean(theta.b) - theta.hat
se = sd(theta.b)

low <- theta.hat - bias - 1.96*se  # 1.96 is for 95% CI

high <- theta.hat - bias + 1.96*se

cat("A",CL,"percent bootstrap CI is", 
    low, high,"\n")



########### Using built-in function in R:


library(boot)
bcor <- function(data, bindex){
  return(cor(data[bindex,1], data[bindex,2]))
}
boot.cor <- boot(law, statistic=bcor, R=2000)

boot.ci(boot.cor,type=c("basic","perc","norm"))



