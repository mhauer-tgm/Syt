CXX            	= /usr/bin/g++
CPPFLAGS      	= -g -c
LDFLAGS       	= -g
OMNI_HOME     	= /home/zeljko/repos/downloads/omniORB/build
#OMNIIDL       	= $(OMNI_HOME)/bin/omniidl
OMNIIDL		= omniidl
LIBS          	= -lomniORB4 -lomnithread -lomniDynamic4
OBJECTS       	= calculatorSK.o server.o
IDL_DIR		= ../idl
IDL_FILE	= $(IDL_DIR)/calculator.idl

all server: $(OBJECTS)
	$(CXX) $(LDFLAGS) -o server server.o calculatorSK.o $(LIBS)

server.o: server.cc
	$(CXX) $(CPPFLAGS) server.cc -I.

calculatorSK.o: calculatorSK.cc calculator.hh
	$(CXX) $(CPPFLAGS) calculatorSK.cc -I.

calculatorSK.cc: $(IDL_FILE)
	$(OMNIIDL) -bcxx $(IDL_FILE)

run: server
	# Start Naming service with command 'omniNames -start -always' as root
	./server -ORBInitRef NameService=corbaname::localhost

nameservice:
	omniNames -start -always &

clean clean-up:
	rm -rf *.o
	rm -rf *.hh
	rm -rf *SK.cc
	rm -rf server
