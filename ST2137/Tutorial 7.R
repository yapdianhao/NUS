magazines = read.table('./weeklies.txt', sep = ',', header = T)
attach(magazines)

t.test(current, lastyear, mu = 0, paired = T, conf.level = 0.95)
diff = current  - lastyear
shapiro.test(diff)

t.test(current, lastyear, mu = 0, paired = T, conf.level = 0.99)

machines = read.table('./machine.txt', sep = ' ', header = T)
attach(machines)
old = strength[which(machine == 'O')]
new = strength[which(machine == 'N')]
var.test(old, new)
shapiro.test(old)
shapiro.test(new)
bartlett.test(strength ~ machine)


wip = read.table('./wip.txt', header = T, sep = ' ')
attach(wip)
plant.1 = time[which(plant == 1)]
plant.2 = time[which(plant == 2)]
wilcox.test(plant.1, plant.2)