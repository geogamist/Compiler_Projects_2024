int fact( int x )
/* recursive factorial function */
{   if (x > 1)
        return x * fact(x-1);
    else
        return 1;
}

void main( void )
{   int x;
    x = read();
    if (x > 0) write( fact(x) );
}