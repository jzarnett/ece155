GOALS = $(wildcard *.fig)
#GOALS =
GOALS_EPS = $(GOALS:.fig=.eps)
GOALS_PDF = $(GOALS:.fig=.pdf)

GOALS_DIA =
#


############################################################



############################################################




all: $(GOALS_EPS) $(GOALS_PDF) $(GOALS_DIA)

%.eps: %.fig
	sh figtex2eps  $<

%.pdf: %.fig
	sh figtex2pdf $<

%.dia.eps: %.dia
	dia --export=`basename $@ .dia.eps`.eps `basename $@ .eps`


clean:
	rm -f $(GOALS_EPS) $(GOALS_PDF)
