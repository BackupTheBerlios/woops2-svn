CC = g++
GNAT = gnatmake
CFLAGS= -ansi
#CGTKFLAGS= `pkg-config --cflags gtk+-2.0` 
LDFLAGS= -ansi  -lpthread -lstdc++ -lgcc_s 
#LGTKFLAGS= `pkg-config --cflags --libs gtk+-2.0` 
EXEC=busnetwork.exe
BIN = NetworkManager.o OperatingCenter.o  interfaceAda.o Interpretor.o
#BINADA = package_busnetwork.o package_busstop.o package_types.o package_constantes.o mainada.o
ALI = package_busnetwork.ali #package_busstop.ali package_types.ali package_constantes.ali

#all: clean $(EXEC)
all: $(EXEC)

$(EXEC) :  $(BIN) $(ALI)
	gnatbind -n $(ALI)
	gnatlink  package_busnetwork.ali -g $(BIN)  -o main --LINK=c++

OperatingCenter.o: ../src_cpp/center/OperatingCenter.h ../src_cpp/center/interfaceAda.h ../src_cpp/center/Types.h
	$(CC) $(LDFLAGS)-o OperatingCenter.o  -c ../src_cpp/center/OperatingCenter.cpp  

NetworkManager.o: ../src_cpp/network/NetworkManager.h
	$(CC) $(LDFLAGS)-o NetworkManager.o  -c ../src_cpp/network/NetworkManager.cpp  

Interpretor.o: ../src_cpp/network/Interpretor.h
	$(CC) $(LDFLAGS)-o Interpretor.o  -c ../src_cpp/network/Interpretor.cpp  

interfaceAda.o: ../src_cpp/center/interfaceAda.cpp ../src_cpp/center/OperatingCenter.h ../src_cpp/center/Types.h
	$(CC) $(CFLAGS) -o interfaceAda.o -c ../src_cpp/center/interfaceAda.cpp

#package_busstop.ali : package_busstop.ads package_busstop.adb
#	$(GNAT) -c package_busstop.adb

#package_types.ali : package_types.adb
#	$(GNAT) -c package_types.adb

#package_constantes.ali : package_constantes.ads
#	$(GNAT) -c package_constantes.ads

package_busnetwork.ali : ../src_ada/package_busnetwork.adb
	$(GNAT) -c ../src_ada/package_busnetwork.adb

clean:
	rm -rf *.o
	rm -rf *.ali
	rm -rf b~*
	rm main

#tar:
#	tar -cvf busnetwork.tar Makefile *.ads *.adb *.hpp *.cpp *.gif *.jpg
