
totalMale_14 = []
totalFemale_14 = []
totalMale_15_64 = []
totalFemale_15_64 = []
totalMale_64_over = []
totalFemale_64_over = []
countries = []

def printMenu():
    response = input("A: Population Information\nB: Population Information by Country Letter\nC: Male/Female Ratios by Age Group\nD: Highest Male/Female Ratios of Different Age Groups\nE: Lowest Male/Female Ratios of Different Age Groups\nF: Numbers of Countries with More Males in Each Age Group\nG: Quit\n\n").lower()
    accept = False
    while accept is not False:
        if response != "a" or response != "b" or response != "c" or response != "d" or response != "e" or response != "f" or response != "g":
            print("A, B, C, D, E, F, or G only.\n")
            response = input("A: Population Information\nB: Population Information by Country Letter\nC: Male/Female Ratios by Age Group\nD: Highest Male/Female Ratios of Different Age Groups\nE: Lowest Male/Female Ratios of Different Age Groups\nF: Numbers of Countries with More Males in Each Age Group\nG: Quit\n\n").lower()
        else:
            accept = True
    return response

def optionSelect(ans):
    if ans == "a":
        option_A()
    if ans == "b":
        option_B()
    if ans == "c":
        option_C()
    if ans == "d":
        option_D()
    if ans == "e":
        option_E()
    if ans == "f":
        option_F
    if ans == "g":
        return "quit"
    
def option_A():
    t=0
    print(format("Males(0-14)",">43s")+format("Females(0-14)",">16s")+format("Males(15-64)",">16s")+format("Females(15-64)",">16s")+format("Males(64+)",">16s")+format("Females(64+)",">16s"))
    for i in countries:
        print(format(i,"<32s") + format(str(totalMale_14[t]),"<14s") + format(str(totalFemale_14[t]),"<14s")+format(str(totalMale_15_64[t]),"<14s") + format(str(totalFemale_15_64[t]),"<14s")+format(str(totalMale_64_over[t]),"<14s") + format(str(totalFemale_64_over[t]),"<14s"))
        t+=1
    
def option_B():
    letter = input("Enter the first letter of countries you wish to see. ").upper()
    
    print(format("Males(0-14)",">43s")+format("Females(0-14)",">16s")+format("Males(15-64)",">16s")+format("Females(15-64)",">16s")+format("Males(64+)",">16s")+format("Females(64+)",">16s"))
    for i in range(0, len(countries)):
        name = countries[i]
        info = format(str(totalMale_14[i]),"<14s") + format(str(totalFemale_14[i]),"<14s") + format(str(totalMale_15_64[i]),"<14s") + format(str(totalFemale_15_64[i]),"<14s") + format(str(totalMale_64_over[i]),"<14s") + format(str(totalFemale_64_over[i]),"<14s")
        if letter == name[0]:
            print (format(name,"<32s") + info)

def option_C():
    t=0
    print(format("Males(0-14)",">43s")+format("Females(0-14)",">16s"))
    for i in countries:
        total = int(totalMale_14[t]) + int(totalFemale_14[t])
        print(format(i,"<32s") + format(str(format(int(totalMale_14[t])/total,".2f"))+"%","<14s") + format(str(format(int(totalFemale_14[t])/total,".2f"))+"%","<19s"))
        t+=1

def option_D():
    sorting_list_m = []
    sorting_list_f = []
    t=0
    
    for i in range(len(countries)):
        sorting_list_m.append([(str(int(totalMale_14[i])/(int(totalMale_14[i])+int(totalFemale_14[i]))), (str(int(totalMale_15_64[i])/(int(totalMale_15_64[i])+int(totalFemale_15_64[i])))), (str(int(totalMale_64_over[i])/(int(totalMale_64_over[i])+int(totalFemale_64_over[i])))))])
        sorting_list_f.append([(str(int(totalFemale_14[i])/(int(totalMale_14[i])+int(totalFemale_14[i]))), (str(int(totalFemale_15_64[i])/(int(totalMale_15_64[i])+int(totalFemale_15_64[i])))), (str(int(totalFemale_64_over[i])/(int(totalMale_64_over[i])+int(totalFemale_64_over[i])))))])

    print("The highest male/female ratio ")
##    for i in countries:
##        print(format(i,"<32s") + format(str(format(float(sorting_list_m[t][0]),".2f")+"%","<14s")) + format(str(format(float(sorting_list_m[t][1]),".2f")+"%","<14s")) + format(str(format(float(sorting_list_m[t][2]),".2f")+"%","<14s")))
##        t+=1
    
def getLists():
    zero_fourteen = open('C:\\Users\\Gerald II\\Documents\\Assignment_8_attached_files\\WorldCensusAges0-14.csv','r')
    fifteen_sixty_four = open('C:\\Users\\Gerald II\\Documents\\Assignment_8_attached_files\\WorldCensusAges15-64.csv','r')
    sixty_four_plus = open('C:\\Users\\Gerald II\\Documents\\Assignment_8_attached_files\\WorldCensusAges64+.csv','r')

    for line in zero_fourteen:
        cData = line.split(",")
        
        cName = cData[0]
        cMale = cData[1]
        cFemale = cData[2]
        
        countries.append(cName)
        totalMale_14.append(cMale)
        totalFemale_14.append(cFemale)

    for line in fifteen_sixty_four:
        cData = line.split(",")
        
        cMale = cData[1]
        cFemale = cData[2]
        
        totalMale_15_64.append(cMale)
        totalFemale_15_64.append(cFemale)

    for line in sixty_four_plus:
        cData = line.split(",")
        
        cMale = cData[1]
        cFemale = cData[2]
        
        totalMale_64_over.append(cMale)
        totalFemale_64_over.append(cFemale)

def main():
    print("Welcome.")
    getLists()    
    ans = printMenu()
    optionSelect(ans)
    
    
main()
    
