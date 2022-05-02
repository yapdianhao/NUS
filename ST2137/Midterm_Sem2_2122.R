# Q1 (R) writing R code with basics of R

Fibo = numeric(45)
Fibo[1:2] = 1
for(i in 3:45) {
Fibo[i] = Fibo[i-1] + Fibo[i-2]
}
Fibo[40]

# the 40th term is: 102334155

n = sum(Fibo<=5000000) + 1 ;n # 34
 # OR can use this code
n = max(which(Fibo<=5000000)) + 1; n

Fibo[n] # 5702887


# Q2 (R) # House hunting
#Q2(1)

cost = 1000000*0.3 # down payment amount

r= 0.01 # investment return rate per month

# salary = monthly salary

F1 = function(salary){ 
  
savings <- 0
  
month <- 0
  
while(savings < cost){
    month = month +1
    savings = savings+ 0.3*salary + savings*r
  }
  return(month)
}


###  Test:

F1(5000) # answer should be 111 months

F1(8000) # answer should be 82 months


#Q2(2) THERE IS A RAISE IN THE SALARY EVERY 6 MONTHS:

F2 <- function(salary, raise) {
  
savings <- 0
  
month <- 0

while(savings < cost){
  
    
    month = month +1
    
    savings = savings+ 0.3*salary + savings*r
    
    if (month%%6 ==0){salary = salary*(1+raise)}
  }
  return(month)
}

F2(5000,0.08) # should be 82 months
F2(8000, 0.02) # should be 76 months



# Q3 (should check the answers given in Python)

oldcars = read.csv("C:/Data/oldcars.csv")
names(oldcars)

attach(oldcars)

length(Selling_Price) # 301 = sample size

#Qa 
table(Fuel_Type) # 239 Petrol and 60 Diesel

#Qb
table(Transmission) # 40 Automatic and 261 Manual


#Qc
mean(Selling_Price)# 4.661296
mean(Selling_Price, trim = 0.2) # 3.543757
# difference = 1.117539
# 1.117539/mean(Selling_Price) = 0.2397486
# That means the original mean is dropped around 24% after trimming.
# That quite large drop suggests the non-symmetric distribution.



#Qd
plot(Present_Price,Selling_Price, pch = 20)

cor(Present_Price,Selling_Price) # 0.8789825

# comment: the scatter plot shows a positive relationship and might be linear.
# There are few points that could be outliers.


#Qe

hist(Selling_Price[Fuel_Type == "Petrol"]) 
# right skewed, max at 20
#should add the normal curbe also.
qqnorm(Selling_Price[Fuel_Type == "Petrol"], pch = 20)
qqline(Selling_Price[Fuel_Type == "Petrol"], col = "red")

shapiro.test(Selling_Price[Fuel_Type == "Petrol"]) # p-value = 1.509e-14

#Qf
hist(Selling_Price[Fuel_Type == "Diesel"]) 
# right skewed, range from 0 to 35
# should add the normal curve to the histogram

qqnorm(Selling_Price[Fuel_Type == "Diesel"], pch = 20)
qqline(Selling_Price[Fuel_Type == "Diesel"], col = "red")

shapiro.test(Selling_Price[Fuel_Type == "Diesel"]) # p-value = 3.6e-07


#Qg 
# should use Levene test to test equality of variances.

t.test(Selling_Price[Fuel_Type == "Petrol"],
       Selling_Price[Fuel_Type == "Diesel"],var.equal = FALSE, alternative = "less"  )
# t = -7.3873, df = 64.74, p-value = 1.826e-10




