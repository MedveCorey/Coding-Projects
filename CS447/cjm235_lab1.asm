# Corey Medve cjm235
.data
	x: .word 0
	y: .word 0
.text

.global main
main:
	# loading immediates into registers
	li t0, 1
	li t1, 2
	li  t2, 3
	
	# moving values between registers
	move a0,t0
	move v0, t1
	move t2, zero
	
	# print 123
	li a0, 123
	li v0, 1
	syscall
	
	# new line
	li a0, '\n'
	li v0, 11
	syscall
	
	# print 456
	li a0, 456
	li v0, 1
	syscall
	
	# new line
	li a0, '\n'
	li v0, 11
	syscall
	
	# Input and global variables
	li v0, 5
	syscall
	sw v0, x # x = v0
	li v0, 5
	syscall
	sw v0, y # y = v0
	
	#add  and print sum
	lw a0, x
	lw a1, y
	add a0, a0, a1
	li v0, 1
	syscall
	
	#exit program
	li v0,10
	syscall
