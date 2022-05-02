
# Tutorial 1 R code and Solution


################ Q1
#Q1a
data = read.csv("C:/Data/final_marks")

names(data)[1] = 'ID'
names(data)[2] = 'mark'

attach(data)

#Q1b
final = data.frame(mark)

attach(final)

mark

# `final` is a dataframe which might have many variables
# while `mark` is a variable in this dataframe.

#Q1c
summary = function(x){
cat("mean is", mean(x),"\n")
cat("lower quartile is", quantile(x)[2],"\n")
cat("median is", median(x),"\n")
cat("upper quartile is", quantile(x)[4],"\n")
cat("variance is", var(x),"\n")
cat("standard deviation is", sd(x),"\n")
cat("the range is", range(x),"\n")

}
summary(mark)

#Q1d
length(which(mark>=40))

#Q1e
which(mark ==max(mark))
length(which(mark ==max(mark)))





##########  Q2
# Matrix:

x<-cbind(rep(1,5),c(1,3,4,7,12))
x

y<-c(4,6,13,15,20)# or

y<-matrix(c(4,6,13,15,20))


beta.hat<-solve(t(x)%*%x) %*% t(x) %*% y
beta.hat


##########  Q3
#Q3a Find the 30th of the given series
x <- numeric(30)
#x[1] <- 0
x[2] <- 1

for(i in 3:30){ x[i] <- 2*x[i-1] - x[i-2] }
x[30]

#Q3b
sum(x[1:20])


################  Q4
# Find the first 4 central moments

moments <- function(x){

s <- numeric(4)

n <- length(x)

s[1] <- mean(x) # 

s[2] <- mean((x-s[1])^2) # when r =2

s[3] <- mean((x-s[1])^3) # when r = 3

s[4] <- mean((x-s[1])^4) # when r = 4

return(s)

}

moments(mark)


