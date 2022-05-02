batfail = read.table('./batfail.txt', header = T, sep = ' ')
attach(batfail)

kruskal.test(time ~ group)

crab = read.csv('./crab.csv', header = T)
attach(crab)
crab$spine = as.factor(crab$spine)

plot(width, weight, type = 'n')
points(width[which(spine == 1)], weight[which(spine == 1)], pch = 20)
points(width[which(spine == 2)], weight[which(spine == 2)], col = "red", pch = 20)
points(width[which(spine == 3)], weight[which(spine == 3)], col = "blue", pch = 20)

s1 = c(rep(0, 173))
s2 = c(rep(0, 173))
s1[which(spine == 1)] = 1
s2[which(spine == 2)] = 1

model = lm(weight ~ width + s1 + s2, data = crab)
summary(model)

anova(model)
confint(model, "width", 0.95)

summary(model)$sigma
summary(model)$r.squared

shapiro.test(rstandard(model))
plot(model$fitted.values, rstandard(model), xlab = "fitted weight", ylab = "standardized residuals", pch = 20)
qqnorm(rstandard(model), datax = T, pch = 20)
qqline(rstandard(model), color = 'red')

predict(model, newdata = data.frame(width = 27, s1= 1, s2 = 0), interval = 'confidence', conf = 0.95)
        