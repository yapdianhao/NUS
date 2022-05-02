flextime = read.table('./flextime.txt', header = T, sep = ' ')
attach(flextime)
diff = after - before

wilcox.test(diff)

# Q2
gasoline = read.csv('./gasoline.csv', header = T)
attach(gasoline)
y_manual = y[which(x11 == 0)]
y_auto = y[which(x11 == 1)]

var.test(y_manual, y_auto)
t.test(y~x11, mu = 0, var.equal = F, conf.level = 0.99)
t.test(y_manual, y_auto, var.equal = F, alternative = 'greater')

# Q3
locate = read.table('./locate.txt', header = T, sep = ' ')
attach(locate)
front.sales = sales[which(location == 'F')]
med.sales = sales[which(location == 'M')]
rear.sales = sales[which(location == 'R')]

shapiro.test(front.sales)
shapiro.test(med.sales)
shapiro.test(rear.sales)

bartlett.test(sales ~ location)
anova = aov(sales ~ location)
summary(anova)
shapiro.test(anova$res)
pairwise.t.test(sales, location, p.adj = 'bonf')
