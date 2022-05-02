
rm(list=ls())
set.seed(99)


#Q1


data<-read.table("C:/Data/midterm_marks", sep = ",", header = TRUE)

mark = data[,2]

mu0 = 20

#test statistic: 
t = (mean(mark - mu0))/sqrt(var(mark)/length(mark)) 
df = length(mark) - 1

#p-value for 2 sided test:
2*pt(t,df)

#p-value for 1 sided left: Ho: mu < mu0
pt(t,df)

#p-value for 1 sided right: Ho: mu > mu0
1 - pt(t,df)



# Forming a confidence interval for population mean:

CI = c( mean(mark) - qt(0.975, df)*sqrt(var(mark)/length(mark)), mean(mark) + qt(0.975, df)*sqrt(var(mark)/length(mark)) )
CI



# built-in function to test:

t.test(mark)# test if mean of mark equal 0 or not

t.test(mark, mu = mu0,alternative = "two.sided") #also produces the CI for population mean


t.test(mark, mu = mu0,alternative = "less")

t.test(mark, mu = mu0,alternative = "greater")


## Checking assumptions made: data is approximately normaly distributed
# histogram
hist(mark, freq=FALSE, main = paste("Histogram of mark"),xlab = "mark", ylab="Probability", axes = TRUE, 
     col = "grey",nclass = 10)

x <- seq(0, 30, 0.05)#length.out=98)
y <- dnorm(x, mean(mark), sd(mark))
lines(x, y, col = "red")

#qq plot
qqnorm(mark,datax = TRUE, ylab = "Sample Quantiels", xlab = "Theorical Quantiles", 
       main = "QQ Plot", pch = 20)
qqline(mark,datax = TRUE)

#Since sample size is large (98), if the distribution of variable mark is 
# not normal, the result of this test above is still reliable, since 
# this test is robust the the normaility assumption.



#Q2
data<-read.table("C:/Data/glaucoma_dep.csv", sep = ",", header = TRUE)

data

attach(data)

#Q1a

shapiro.test(diff)

t.test(diff, mu = 0,alternative = "less")

#p-value = 0.04841, less than 0.05. Hence, we reject Ho, and conclude:
#glaucoma decreases the thickness of the corneal.


#Q1b
var.test(glaucoma,unaffected) # variances are equal since p-value = 0.8 is large.

t.test(glaucoma,unaffected, mu = 0, var.equal = TRUE, alternative = "less") 

# p-value = 0.3417 > 0.05. Do not reject Ho. conclude: no evidence to show 
#that glaucoma decreases the corneal's thickness.
#the conclusion is different, since the samples (glaucoma and unaffected are treated as independent.




############

### television debates

#### paired data, hence McNemar test for paired nominal data should be used

#For Males:
debateM<- matrix(c(67,28,46,54), nr=2, byrow=T)
colnames(debateM) <- c("Candidate A", "Candidate B")
rownames(debateM) <- c("Candidate A", "Candidate B")
debateM

mcnemar.test(debateM) # with continuity correction 
# same result as in Python: 


# For Females:

debateF <- matrix(c(58,42,37,61), nr=2, byrow=T)
colnames(debateF) <- c("Candidate A","Candidate B")
rownames(debateF) <- c("Candidate A","Candidate B")
debateF

mcnemar.test(debateF) # with continuity correction 

mcnemar.test(debateF, correct = FALSE) # without continuity correction 

#mcnemar(debateM, correction = True, exact = False)


# Both tests for male group and female group have similar set of hypothese:
# H0 : the opinion of voters before and after the debate are independent vs
# H1 : the opinion of voters changes after the debate:

# The debate has no effect on changing the opinions of the female voters (large p-value means data do not
# provide evidence against H0, meaning the opinion before and after are independent), but the debate
# has an effect on the male voters' opinions (small p-value means data provide strong evidence against H0).



