data = read.table('./ex10_1.txt', header = T)
attach(data)
cor(cbind(height, weight, age), method = "pearson")

model1 = lm(weight ~ height, data = data)
summary(model1)

anova(model1)

model2 = lm(weight ~ height + age, data = data)
summary(model2)

anova(model2)

data$gender = as.factor(data$gender)

model3 = lm(weight ~ height + age + gender, data = data)
summary(model3)
