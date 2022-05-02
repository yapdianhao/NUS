data = read.csv('./tablets1.txt', sep = ',', header = T)
names(data) = c('lab1', 'lab2', 'lab3', 'lab4', 'lab5', 'lab6', 'lab7')
attach(data)
amount = c(lab1, lab2, lab3, lab4, lab5, lab6, lab7)
lab = c(rep(1, 10), rep(2, 10), rep(3, 10), rep(4, 10), rep(5, 10), rep(6, 10), rep(7, 10))
newdata = data.frame(amount = amount, lab = lab)
attach(newdata)
newdata$lab = as.factor(newdata$lab)

## anova test
anova = aov(amount~lab, data = newdata)
summary(anova)

## kruskal test
kruskal.test(amount ~ lab)

shapiro.test(anova$residuals)

TukeyHSD(anova)
