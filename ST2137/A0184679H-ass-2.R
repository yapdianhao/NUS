install.packages('lawstat')
install.packages('broom')
library(lawstat)
library(broom)

crab = read.csv('crab.csv', header = T)
tablets = read.table('tablets1.txt', header = T, sep = ',')
locate = read.table('locate.txt', header = T)


# process tablets data
names(tablets) = c('lab1', 'lab2', 'lab3', 'lab4', 'lab5', 'lab6', 'lab7')
attach(tablets)
amount = c(lab1, lab2, lab3, lab4, lab5, lab6, lab7)
group = c(rep(1, 10), rep(2, 10), rep(3, 10), rep(4, 10), rep(5, 10), rep(6, 10), rep(7, 10))
tablets= data.frame(amount = amount, group = group)
attach(tablets)
# tablets


# process locate data
names(locate) = c('amount', 'group')
# locate

# process crab data
crab.df = data.frame(crab$weight, crab$spine)
names(crab.df) = c('amount', 'group')
# meanWeight.one = mean(crab.df[which(crab.df$spine == 1), 1])
# meanWeight.two = mean(crab.df[which(crab.df$spine == 2), 1])
# meanWeight.three = mean(crab.df[which(crab.df$spine == 3), 1])
# crab.mean.df = rbind(c(meanWeight.one, 1), c(meanWeight.two, 2), c(meanWeight.three, 3))
# row.names(crab.mean.df) = c(1,2,3)
# colnames(crab.mean.df) = c('amount', 'group')
# crab.mean.df = data.frame(crab.mean.df)

normtest = function(df) {
  uniqueLength = length(unique(df$group))
  norm.pValues = rep(0, uniqueLength)
  counter = 1
  for (groupId in unique(df$group)) {
    subdata = df[which(df$group == groupId), 1]
    norm.pValues[counter] = shapiro.test(subdata)$p.value
    counter = counter + 1
  }
  return(norm.pValues)
}

EVT = function(df, alpha) {
  norm.pValues = normtest(df)
  allNormData = TRUE
  for (pValue in norm.pValues) {
    if (pValue < alpha) {
      allNormData = FALSE
      break
    }
  }
  if (allNormData) {
    print("Using Bartlett for equal variance test")
    return (bartlett.test(df$amount, df$group)$p.value)
  } else {
    print("Using Levene for equal variance test")
    return (levene.test(df$amount, df$group)$p.value)
  }
}

Bonf.PWT = function(df, norm.alpha = 0.05, var.alpha = 0.05) {
  equalVariance.alpha = EVT(df, norm.alpha)
  if (equalVariance.alpha <= var.alpha) {
    # do ptt with non equal variance
    print('Pairwise t-test with non-equal variance')
    testBeforeCorrect = pairwise.t.test(df[,1], df[,2], p.adj = 'none', pool.sd = F)
    testAfterCorrect = pairwise.t.test(df[,1], df[,2], p.adj = 'bonf', pool.sd = F)
    processedDataBeforeCorrect = data.frame(tidy(testBeforeCorrect))
    processedDataAfterCorrect = data.frame(tidy(testAfterCorrect))
    comparisonDf = data.frame(processedDataBeforeCorrect, processedDataAfterCorrect$p.value)
    names(comparisonDf) = c('group 1', 'group 2', 'original p-value', 'adjusted p-value')
    return(comparisonDf)
  } else {
    # do ptt with equal variance
    print('Pairwise t-test with equal variance')
    testBeforeCorrect = pairwise.t.test(df[,1], df[,2], p.adj = 'none', pool.sd = T)
    testAfterCorrect = pairwise.t.test(df[,1], df[,2], p.adj = 'bonf', pool.sd = T)
    processedDataBeforeCorrect = data.frame(tidy(testBeforeCorrect))
    processedDataAfterCorrect = data.frame(tidy(testAfterCorrect))
    comparisonDf = data.frame(processedDataBeforeCorrect, processedDataAfterCorrect$p.value)
    names(comparisonDf) = c('group 1', 'group 2', 'original p-value', 'adjusted p-value')
    return(comparisonDf)
  }
}

Bonf.PWT(tablets)
# [1] "Using Levene for equal variance test"
# [1] "Pairwise t-test with equal variance"
# group 1 group 2 original p-value adjusted p-value
# 1        2       1   0.019449465398    0.40843877337
# 2        3       1   0.033247931303    0.69820655736
# 3        3       2   0.825517897498    1.00000000000
# 4        4       1   0.000001985251    0.00004169027
# 5        4       2   0.006051481809    0.12708111798
# 6        4       3   0.003227942196    0.06778678611
# 7        5       1   0.000257432096    0.00540607401
# 8        5       2   0.144972876837    1.00000000000
# 9        5       3   0.094595548241    1.00000000000
# 10       5       4   0.177066182279    1.00000000000
# 11       6       1   0.000201388332    0.00422915496
# 12       6       2   0.126244032166    1.00000000000
# 13       6       3   0.081400350572    1.00000000000
# 14       6       4   0.201302776028    1.00000000000
# 15       6       5   0.941410457130    1.00000000000
# 16       7       1   0.021315880297    0.44763348624
# 17       7       2   0.970684975883    1.00000000000
# 18       7       3   0.854231362680    1.00000000000
# 19       7       4   0.005460568910    0.11467194711
# 20       7       5   0.135352378378    1.00000000000
# 21       7       6   0.117631238327    1.00000000000
Bonf.PWT(locate)
# [1] "Using Bartlett for equal variance test"
# [1] "Pairwise t-test with equal variance"
# group 1 group 2 original p-value adjusted p-value
# 1       M       F     0.0001404965     0.0004214895
# 2       R       F     0.0071957320     0.0215871960
# 3       R       M     0.0696430729     0.2089292186
Bonf.PWT(crab.df)
# [1] "Using Levene for equal variance test"
# [1] "Pairwise t-test with equal variance"
# group 1 group 2 original p-value adjusted p-value
# 1       2       1      0.002609599      0.007828796
# 2       3       1      0.008621301      0.025863902
# 3       3       2      0.113053543      0.339160630





