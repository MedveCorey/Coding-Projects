# Corey Medve
# cjm235

.include "lab4_include.asm"

.eqv NUM_DOTS 3

.data
	dotX: .word 10, 30, 50
	dotY: .word 20, 30, 40
	curDot: .word 0
.text
.globl main
main:
	# when done at the beginning of the program, clears the display
	# because the display RAM is all 0s (black) right now.
	jal display_update_and_clear

	_loop:
		jal check_inputs
		jal wrap_dot_position
		jal draw_dots
		jal display_update_and_clear
		jal sleep
	j _loop

	li v0, 10
	syscall

#-----------------------------------------

draw_dots:
	push ra
	push s0
	li s0, 0
#for(int i = 0; i <NUM_DOTS; i++)
_loop:
	mul t0, s0, 4
	lw a0, dotX(t0)
	lw a1, dotY(t0)
	lw  t0 curDot
	#if(s0 ==curDot){
	bne s0, t0, _else
		li a2, COLOR_ORANGE
	j _endif
	_else:
		li a2, COLOR_WHITE
	_endif:
	jal display_set_pixel
	
	add s0, s0, 1
	blt s0, NUM_DOTS, _loop
	pop s0
	pop ra
jr ra

check_inputs:
	push ra
	jal input_get_keys_held
	and t0, v0, KEY_Z
	beq t0, 0, _endif_z
		li t0, 0
		sw t0, curDot
	 _endif_z:
     	and t0, v0, KEY_X
     	beq t0, 0, _endif_x
     		li t0, 1
     	 	sw t0, curDot
	 _endif_x:
     	and t0, v0, KEY_C
     	beq t0, 0, _endif_c
     		li t0, 2
     		sw t0, curDot
	_endif_c:
	lw t9, curDot
	mul t9, t9,4
	and t0,v0, KEY_R
	beq t0, 0, _endif_r
		lw a0, dotX(t9)
		add a0, a0, 1
		sw a0, dotX(t9)
	_endif_r:
	lw t9, curDot
	mul t9, t9,4
	and t0,v0, KEY_L
	beq t0, 0, _endif_l
		lw a0, dotX(t9)
		sub a0, a0, 1
		sw a0, dotX(t9)
	_endif_l:
	lw t9, curDot
	mul t9, t9,4
	and t0,v0, KEY_D
	beq t0, 0, _endif_d
		lw a1, dotY(t9)
		add a1, a1, 1
		sw a1, dotY(t9)
	_endif_d:
	lw t9, curDot
	mul t9, t9,4
	and t0,v0, KEY_U
	beq t0, 0, _endif_u
		lw a1, dotY(t9)
		sub a1, a1, 1
		sw a1, dotY(t9)
	_endif_u:
	pop ra
jr ra
wrap_dot_position:
	push ra
	
	lw t9 curDot
	mul t9,t9,4
	#dotX[curDot] = dotX[curDot] & 63
	lw a0, dotX(t9)
	and a0,a0, 63
	sw a0, dotX(t9) 
	#dotY[curDot] = dotY[curDot] & 63
	lw a1 dotY(t9)
	and a1,a1 63
	sw a1 dotY(t9)
	pop ra
jr ra
