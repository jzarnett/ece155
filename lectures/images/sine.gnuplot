set samples 3001
#set terminal postscript enhanced landscape color solid lw 2 "Times-Roman" 20
set terminal svg size 1000 750 fname "Times New Roman" fsize 20
set output "sine.svg"

set xrange [-2*pi:2*pi]
set xtics ('-2π' -2*pi, '-π' -pi, 0, 'π' pi, '2π' 2*pi)
plot sin(x*pi)*x with lines lw 2 lc rgb "blue"

set output "sine-points.svg"
set xrange [-2*pi:2*pi]
#plot sin(x*pi)*x with lines lw 2 lc rgb "blue"
set style line 1 lc rgb "blue"
set samples 500
set nokey
plot sin(x*pi)*x with lines ls 1,sin(x*pi)*x with linespoints pointinterval 10 pointtype 7 pointsize 0.5 lc rgb "blue"
