import xlrd
from collections import OrderedDict
import simplejson as json

# Open the workbook and select the first worksheet
wb = xlrd.open_workbook('cnu_major.xls')
sh = wb.sheet_by_index(0)

# Dictionary to hold dictionaries
major_dict = {}
major = {}

# Iterate through each row in worksheet and fetch values into dict
for rownum in range(1, sh.nrows):
    majors = OrderedDict()
    row_values = sh.row_values(rownum)
    try:
        #majors['index'] = int(row_values[0])
        #majors['major'] = row_values[1]
        major_dict[int(row_values[0])] = row_values[1] 
    except ValueError:
        pass

major['majors'] = major_dict

# Serialize the lost of dicts to JSON
j = json.dumps(major, ensure_ascii=False, encoding='utf8')

# Write to file
with open('data.json', 'w') as f:
    f.write(j)