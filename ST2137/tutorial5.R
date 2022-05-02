student = read.table('student.txt', sep = ",", header = T)

attach(student)

gendergp <- ifelse(gender=="F","Female","Male") 
drivegp = ifelse(drivelic == 'N', "No", "Yes")
travelgp = ifelse(travel == 'N', "No", "Yes")

table(gendergp)
table(drivegp)
table(travelgp)

tb = table(gendergp, drivegp)
prop.table(tb, "gendergp")

OR<-function(x, pad.zeros = FALSE, conf.level=0.95){
  if(pad.zeros){if(any(x==0)) {x<-x+0.5}}
  theta<-x[1,1]*x[2,2]/(x[2,1]*x[1,2])
  ASE<-sqrt(sum(1/x))
  CI<-exp(log(theta) +c(-1,1)*qnorm(0.5*(1+conf.level))*ASE)
  list(estimator=theta, ASE=ASE,conf.interval=CI, 
       conf.level=conf.level) 
}

OR(tb)
chisq.test(tb)

whg = c()
for (value in workhour) {
  if (value == 0) {
    whg = append(whg, "None")
  } else if (value < 20) {
    whg = append(whg, "Some")
  } else {
    whg = append(whg, "Many")
  }
}

table(whg)
chisq.test(whg, travelgp)

#########
samoa = read.csv('samoa.csv')


test = matrix(c(16, 654, 24, 306), nrow = 2, byrow = T)

