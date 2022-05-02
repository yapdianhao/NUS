# messages.asm
  .data
str: .asciiz "the answer = "
  .text

main:

    li   $v0, 5   # system call code for read_int
    syscall        # print the integer

    la $t0, ($v0) # transfer the input to somewhere else

    li   $v0, 4    # system call code for print_string
    la   $a0, str  # address of string to print
    syscall

    li   $v0, 1    # system call code for print_int
    la   $a0, ($t0)   # integer to print
    syscall

    li   $v0, 10   # system call code for exit
    syscall        # terminate program
