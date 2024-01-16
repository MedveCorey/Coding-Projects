# Corey Medve
# cjm235

# preserves a0, v0
.macro print_str %str
	.data
	print_str_message: .asciiz %str
	.text
	push a0
	push v0
	la a0, print_str_message
	li v0, 4
	syscall
	pop v0
	pop a0
.end_macro

# -------------------------------------------
.eqv ARR_LENGTH 5
.data
	arr: .word 100, 200, 300, 400, 500
	message: .asciiz "testing!"
.text
# -------------------------------------------
.globl main
main:
	jal input_arr
	jal print_arr
	jal print_char
	# exit()
	li v0, 10
	syscall
# -------------------------------------------
input_arr:
push ra
	li t0, 0
_loop:
	#System.out.print("enter value: ")
	print_str "enter value: "
	li v0 5
	syscall
	move a0, v0
	#arr[i] = syscall_5( );
	mul t1, t0, 4
	sw a0, arr(t1)
	
	add t0, t0, 1
	blt t0, ARR_LENGTH, _loop
pop ra
jr ra
# -------------------------------------------
print_arr:
push ra
	li t0, 0
# for (t0 = 0; t0 < arr.length; t0++)
_loop:
	#system.out.print("arr[");
	print_str "arr["
	#System.out.print(t0);
	move a0, t0
	li v0, 1
	syscall
	#System.out.print("] = ");
	print_str "] = "
	#System.out.print( arr[t0] );
	mul t1, t0, 4
	lw a0, arr(t1)
	li v0 1
	syscall
	#System.out.print("\n")
	print_str " \n"
	add t0, t0, 1
	blt t0, ARR_LENGTH, _loop

pop ra
jr ra
# -------------------------------------------
print_char:
push ra
lb a0,  message
li t0, 0
_loop:
	lb a0 message(t0)
	beq a0, 0, _end
	li v0, 11
	syscall
	print_str"\n"
	
	add t0, t0, 1
	j _loop
_end:

pop ra
jr ra
# -------------------------------------------
