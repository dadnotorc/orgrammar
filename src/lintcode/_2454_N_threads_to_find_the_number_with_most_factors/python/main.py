from solution import Solution
from solution import FindNumWithMostFactorsConcurrently
import sys
import json


input_path = sys.argv[1]
output_path = sys.argv[2]
input_file = open(input_path, 'r')
output_file = open(output_path, 'w')

n = json.loads(input_file.readline())
m = json.loads(input_file.readline())
input_file.close()

solution = Solution()
most_factors_num = solution.find_num_with_most_factors(n, m)

if FindNumWithMostFactorsConcurrently.call_search_range_count != 0:
    output_file.write(json.dumps(most_factors_num))
else:
    output_file.write(json.dumps('You must call function FindNumWithMostFactorsConcurrently.search_range'))

output_file.close()
