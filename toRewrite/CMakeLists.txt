cmake_minimum_required(VERSION 3.14)
set(CMAKE_CXX_COMPILER g++)
set(CMAKE_C_COMPILER gcc)

project(Cinema)

set(CMAKE_CXX_STANDARD 14)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
set(CMAKE_VERBOSE_MAKEFILE TRUE)

set(SOURCE_FILES
        src/Client.cpp include/Client.h
        src/ClientRepository.cpp include/ClientRepository.h
        src/ClientManager.cpp include/ClientManager.h
        include/Repository.h
        src/Movie.cpp include/Movie.h
        src/MovieRepository.cpp include/MovieRepository.h
        src/Ticket.cpp include/Ticket.h
        src/TicketRepository.cpp include/TicketRepository.h
        src/TicketManager.cpp include/TicketManager.h
        src/MovieManager.cpp include/MovieManager.h
        src/NormalClient.cpp include/NormalClient.h
        src/PremiumClient.cpp include/PremiumClient.h
        src/RepositoryException.cpp include/RepositoryException.h)

add_executable(Cinema src/main.cpp ${SOURCE_FILES})
target_include_directories(Cinema PUBLIC include)

#Sekcja testów
find_package (Boost 1.60.0 COMPONENTS "unit_test_framework" REQUIRED)

include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}/include
        ${Boost_INCLUDE_DIRS}
)

set(SOURCE_TEST_FILES
        test/master.cpp test/MovieTest.cpp test/ClientTest.cpp test/TicketTest.cpp)

add_executable(Testy ${SOURCE_TEST_FILES} ${SOURCE_FILES})

target_include_directories(Testy PUBLIC include)

target_link_libraries(Testy
        ${Boost_UNIT_TEST_FRAMEWORK_LIBRARY}
        )

enable_testing()
add_test(TestCinema Testy
        --report_level=detailed
        --log_level=all
        --color_output=yes
        )
add_custom_target(check ${CMAKE_COMMAND} -E env CTEST_OUTPUT_ON_FAILURE=1 BOOST_TEST_LOG_LEVEL=all
        ${CMAKE_CTEST_COMMAND} -C $<CONFIG> --verbose
        WORKING_DIRECTORY ${CMAKE_BINARY_DIR})