data = read.table('wip.txt', header = TRUE, sep = " ")
attach(data)

plant.1 = data$time[which(data$plant == 1)]
plant.2 = data$time[which(data$plant == 2)]

summaries = function(x) {
  return(list(
    mean = mean(x),
    median = median(x),
    summary = summary(x),
    min = min(x),
    max = max(x),
    range = range(x),
    iqr = IQR(x),
    variance = var(x),
    stdev = sd(x)
  ))
}

par(mfrow=c(2,2))

plot.hist = function(x) {
  hist(x, freq = F, main= "Plant 1 time Historgram")
  xpoint = seq(0, 40, 0.05)
  ypoint = dnorm(xpoint, mean(x), sd(x))
  lines(xpoint, ypoint, col="red")
}

plot.box = function(x) {
  boxplot(x)
}


testscores = read.table('testscores.txt', sep = " ", header = T)
attach(testscores)

males = testscores[gender == 'M', c(1:2)]
females = testscores[gender == 'F', c(1:2)]
plot(A, B, xlab = "A score", ylab = "B score")
points(males$A, males$B, pch = 20)
points(females$A, females$B, pch = 20, col = "red")

par(mfrow=c(1,2))
plot(males$A, males$B, xlab = "Male A score", ylab = "Male B Score", pch = 20)
plot(females$A, females$B, xlab = "Female A score", ylab = "Female B score", pch = 20)

par(mfrow=c(1,1))


furniture = read.table('furniture.txt', sep = ' ', header = T)
attach(furniture)

mean(days, trim = 0.2)

winsor<-function(x, alpha = 0.2) 
{ 
  n = length(x)
  xq = n * alpha
  
  x = sort(x)
  
  m = x[(round(xq)+1)]
  
  M = x[(n - round(xq))]
  
  x[which(x<m)] = m
  
  x[which(x>M)] = M
  
  return(c(mean(x),var(x)))  #return winsorized mean and variance of winsorized data
}

IQR(days) / 1.35
sd(days)
median(abs(days - median(days)))
mad(days)
