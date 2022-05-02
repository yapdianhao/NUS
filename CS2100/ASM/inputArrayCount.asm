# arrayCount.asm
  .data
arrayA: .word 0, 0, 0, 0, 0, 0, 0, 0  # arrayA has 5 values
count:  .word 0             # dummy value

  .text
main:
    # code to setup the variable mappings
    la $t0, arrayA     #s0 -> start of array
    la $t8, count      #t8 -> end of array
    addi $a0, $zero, 0 #a0 -> to print
    addi $t2, $t0, 0   #t2 -> read array counter

input:
    beq $t2, $t8, readx
    li $v0, 5
    syscall
    sw $v0, ($t2)
    addi $t2, $t2, 4
    j input

readx:
    # code for reading in the user value X
    li $v0, 5
    syscall
    addi $s3, $v0, 0   #s3 ->
    addi $s2, $s3, -1  #s2 -> mask

loop:
    # code for counting multiples of X in arrayA
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
