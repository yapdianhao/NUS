# arrayCount.asm
  .data
arrayA: .word 11, 0, 31, 22, 9, 17, 6, 9   # arrayA has 5 values
count:  .word 0             # dummy value

  .text
main:
    # code to setup the variable mappings
    la $t0, arrayA
    #addi $t1, $t0, 32
    la $t8, count
    addi $a0, $zero, 0

    # code for reading in the user value X
    li $v0, 5
    syscall

loop:
    # code for counting multiples of X in arrayA
    addi $s2, $v0, -1
    beq $t0, $t8, print
    lw $s0, ($t0)
    and $s1, $s0, $s2
    beq $s1, $zero, inc
    addi $t0, $t0, 4
    j loop
inc:
    addi $a0, $a0, 1
    addi $t0, $t0, 4
    j loop

    # code for printing result
print:
    li $v0, 1
    syscall

exit:
    # code for terminating program
    sw $a0, count
    li  $v0, 10
    syscall
