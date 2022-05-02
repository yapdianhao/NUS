## 1
Fibo = c(1:45)
Fibo[1] = 1
Fibo[2] = 1
index = 3
while (index <= 45) {
  Fibo[index] = Fibo[index - 1] + Fibo[index - 2]
  index = index + 1
}

Fibo[40] # 102334155

smallestValue = Fibo[1]
for (val in Fibo) {
  if (val >= 5000000) {
    smallestValue = val
    break
  }
}

smallestValue # 5702887

## 2
# (a)
F1 = function(salary) {
  flat.price = 1000000
  months = 0
  savings = 0
  while (savings < flat.price) {
    savings = savings * 1.01
    savings = savings + salary * 0.3
    months = months + 1
  }
  return(months)
}

F1(5000) # 205
F1(8000) # 166

#(b)
F2 = function(salary, raise) {
  flat.price = 1000000;
  months = 0;
  savings = 0;
  while (savings < flat.price) {
    savings = savings * 1.01;
    savings = savings + salary * 0.3;
    months = months + 1;
    if ((months + 1) %% 6 == 0) {
      salary = salary * (1 + raise);
    }
  }
  return(months);
}

F2(5000, 0.08) # 140
F2(8000, 0.02) # 150
