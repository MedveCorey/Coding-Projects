#Corey Medve
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
.data
	display: .word 0
	operation: .word 
.text
	
.globl main
main:
	print_str "Hello! Welcome! \n"
	#while(true){
_loop:
	#System.out.print(display)
	lw a0, display
	li v0, 1 
	syscall
	#System.out.print( "\nOperation (=,+,-,*,/,c,q):")
	print_str "\nOperation (=,+,-,*,/,c,q): "
	li v0, 12
	syscall
	#operation = read_char( )
	sw v0, operation
	#System.out.print("\n")
	print_str "\n"
	
	# switch(operation) {
	lw  t0, operation
	beq t0, 'q', _quit
	beq t0, 'c', _clear
	beq t0, '+',_get_operand
	beq t0, '-',_get_operand
	beq t0, '=',_get_operand
	beq t0, '/',_get_operand
	beq t0, '*',_get_operand
	j   _default

	# case 'q':
	_quit:
		print_str "quit\n"
		li v0, 10
		syscall
		j _break

	# case 'c'
	_clear:
		print_str "clear\n"
		sw zero,display
		j _break
		
	#case: 'get operand'
	_get_operand:
		beq t0, '+',_add
		beq t0, '-', _subtract
		beq t0, '*',_multiply
		beq t0, '/',_divide
		beq t0,'=',_equals
		j _default
		#case '+'
		_add:
			print_str "\nValue:"
			li v0, 5
			syscall
			lw a0, display
			add a0, a0, v0
			sw a0, display
			
			j _break
		
		#case: '-'
		_subtract:
			print_str "Value:"
			li v0, 5
			syscall
			lw a0, display
			sub a0, a0, v0
			sw a0, display
			j _break
			
		#case '*'
		_multiply:
			print_str "Value:"
			li v0, 5
			syscall
			lw a0, display
			mul a0, a0, v0
			sw a0, display
			j _break
		#case: '/'
		_divide:
			print_str "Value:"
			li v0, 5
			syscall
			#if-else statement
			bne v0, 0, _else
				print_str "Divide by zero error\n"
				j _divide
			_else:
				lw a0, display
				div a0, a0, v0
				sw a0, display
			j _break
		#case: '='
		_equals:
		print_str "Value:"
			li v0, 5
			syscall
			sw v0, display
	j _break
	# default:
	_default:
		print_str "Huh?\n"
		# no j _break needed cause it's the next line.
_break:
	
	#}
	j _loop
