# arrayFunction.asm
       .data
array: .word 8, 2, 1, 6, 9, 7, 3, 5, 0, 4
newl:  .asciiz "\n"

       .text
main:
	########################## Print the original content of array #########################
	# setup the parameter(s)
  la $a0, array       # load array address into $a0
  li $a1, 10            # array elements
	jal printArray        # call the printArray function

	###############################Ask the user for two indices ############################
	li   $v0, 5         	# System call code for read_int
	syscall
	addi $t0, $v0, 0    	# first user input in $t0

	li   $v0, 5         	# System call code for read_int
	syscall
	addi $t1, $v0, 0    	# second user input in $t1


	############################## Call the findMin function ############################
	# setup the parameter(s)
  la $a0, array
  li $a1, 10
	jal findMin           # call the function


	# Print the min item
	# place the min item in $t3	for printing

	# Print an integer followed by a newline
	li   $v0, 1   		# system call code for print_int
    addi $a0, $t3, 0    # print $t3
    syscall       		# make system call

	li   $v0, 4   		# system call code for print_string
    la   $a0, newl    	#
    syscall       		# print newline

	#Calculate and print the index of min item

	# Place the min index in $t3 for printing
  addi $t3, $t5, 0

	# Print the min index
	# Print an integer followed by a newline
	li   $v0, 1   		# system call code for print_int
    addi $a0, $t3, 0    # print $t3
    syscall       		# make system call

	li   $v0, 4   		# system call code for print_string
    la   $a0, newl    	#
    syscall       		# print newline

	# End of main, make a syscall to "exit"
	li   $v0, 10   		# system call code for exit
	syscall	       	# terminate program


#######################################################################
###   Function printArray   ###
#Input: Array Address in $a0, Number of elements in $a1
#Output: None
#Purpose: Print array elements
#Registers used: $t0, $t1, $t2, $t3
#Assumption: Array element is word size (4-byte)
printArray:
	addi $t1, $a0, 0	#$t1 is the pointer to the item
	sll  $t2, $a1, 2	#$t2 is the offset beyond the last item
	add  $t2, $a0, $t2 	#$t2 is pointing beyond the last item
l1:
	beq  $t1, $t2, e1
	lw   $t3, 0($t1)	#$t3 is the current item
	li   $v0, 1   		# system call code for print_int
     	addi $a0, $t3, 0    	# integer to print
     	syscall       		# print it
	addi $t1, $t1, 4
	j l1				# Another iteration
e1:
	li   $v0, 4   		# system call code for print_string
     	la   $a0, newl    	#
     	syscall       		# print newline
	jr $ra			# return from this function


#######################################################################
###   Student Function findMin   ###
#Input: Lower Array Pointer in $a0, Higher Array Pointer in $a1
#Output: $v0 contains the address of min item
#Purpose: Find and return the minimum item
#              between $a0 and $a1 (inclusive)
#Registers used: <Fill in with your register usage>
#Assumption: Array element is word size (4-byte), $a0 <= $a1
findMin:
  addi $t4, $t0, 0   # t4 = currindex, starting index at t0
  addi $t5, $t4, 0   # t5 = minindex
  sll $t0, $t0, 2     # integers * 4
  sll $t1, $t1, 2     # integers * 4
  add $a1, $t1, $a0  # array end index now at A[y]
  add $a0, $t0, $a0  # array start index now at A[x]
  addi $a1, $a1, 4
  lw $s0, ($a0)      # loads first element
  addi $s3, $s0, 0 #set min to large number
loopfindmin:
  beq $a0, $a1, endfindmin # if loop
  lw $s0, ($a0) #load word from address t0 to s0
  addi $a0, $a0, 4    # next element
  slt $s1, $s0, $s3   # if s0 < t3, s1 = true
  bne $s1, $zero, updatemin
  addi $t4, $t4, 1    #if not smaller, continue to increment t4
  j loopfindmin
updatemin:
  addi $s3, $s0, 0    #t3 = s0
  addi $t5, $t4, 0    # update minindex
  addi $t4, $t4, 1    # still increment t4
  j loopfindmin
endfindmin:
  addi $t3, $s3, 0
	jr $ra			# return from this function
