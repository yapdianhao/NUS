midterm = read.csv('midterm_marks', header = T)
names(midterm) = c("i", "mark")
attach(midterm)

mean(mark)
sd(mark)

n = length(mark)
test_statistic = (mean(mark) - 20) / sqrt(var(mark) / n)
2 * pt(test_statistic, n - 1)
1 - pt(test_statistic, n - 1)

t.test(mark, mu = 20)

glaucoma = read.csv('glaucoma_dep.csv', header = T)
attach(glaucoma)

diff = glaucoma$glaucoma - glaucoma$unaffected
wilcox.test(diff)
