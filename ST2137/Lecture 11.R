law = read.csv('./lawschool.csv')
attach(law)
cor(LSAT, GPA)
set.seed(999)
R = 1000
n = length(GPA)
theta.b = numeric(R)
for (b in 1:R) {
  i = sample(1:n, size = n, replace = T)
  print(i)
  LSATb = LSAT[i]
  GPAb = GPA[i]
  theta.b[b] = cor(LSATb, GPAb)
}
sd(theta.b)

library(boot)
bcor = function(data, bidx) {
  return (cor(data[bidx, 1], data[bidx,2]))
}

