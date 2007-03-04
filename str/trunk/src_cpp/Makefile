CC = g++
GNAT = gnatmake
CFLAGS= -ansi
#CGTKFLAGS= `pkg-config --cflags gtk+-2.0` 
LDFLAGS= -ansi  -lpthread -lstdc++ -lgcc_s 
#LGTKFLAGS= `pkg-config --cflags --libs gtk+-2.0` 
EXEC=busnetwork.exe
BIN = OperatingCenter.o  main.o
BINADA = package_busnetwork.o package_busstop.o package_types.o package_constantes.o 
ALI = package_busnetwork.ali package_busstop.ali package_types.ali package_constantes.ali

all: clean $(EXEC)

$(EXEC) :  $(BIN) $(ALI)
	gnatbind -n $(ALI)
	gnatlink  package_busnetwork.ali -g $(BIN)  -o main

OperatingCenter.o: OperatingCenter.cpp Types.h
	$(CC) $(LDFLAGS)-o OperatingCenter.o  -c OperatingCenter.cpp  

main.o: main.cpp OperatingCenter.h Types.h
	$(CC) $(CFLAGS) -o main.o -c main.cpp

package_busstop.ali : package_busstop.ads package_busstop.adb
	$(GNAT) -c package_busstop.adb

package_types.ali : package_types.adb
	$(GNAT) -c package_types.adb

package_constantes.ali : package_constantes.ads
	$(GNAT) -c package_constantes.ads

package_busnetwork.ali : package_busnetwork.adb
	$(GNAT) -c package_busnetwork.adb

clean:
	rm -rf *.o
	rm -rf *.ali

#tar:
#	tar -cvf busnetwork.tar Makefile *.ads *.adb *.hpp *.cpp *.gif *.jpg
