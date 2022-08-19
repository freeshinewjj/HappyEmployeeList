package com.happygroup.employeelist.domain

import com.happygroup.employeelist.model.EmployeeInfo
import com.happygroup.employeelist.data.repository.EmployeesRepository

/**
 * This use case randomly selects an endpoint according to its weight.
 * For example, we have 3 endpoints with weights [3,2,1], the first endpoint (index 0) is getting picked 3/(3+2+1) by chance on average
 */
class GetEmployeesWithRandomEndpointWithWeightUseCase(
    private val repository: EmployeesRepository,
    private val endpoints: List<String>,
    weights: List<Int>
    ) {
    private val preSum = mutableListOf<Double>()

    suspend operator fun invoke(): List<EmployeeInfo> {
        val sum = preSum.last()
        val target = sum * Math.random()
        val tgtIdx = lowerBound(target)
        return repository.getAllEmployees(endpoints[tgtIdx])
    }

    /**
     * returns the first element position in the preSum which compares greater than or equal to key using binary search. time complexity: O(logn)
     * This is the same to C++ lower_bound api
     */
    private fun lowerBound(key: Double): Int {
        val n = preSum.size
        var s = 0
        var e = n -1
        while (s <= e) {
            val m = (s + e) / 2
            if (preSum[m] >= key) {
                e = m -1
            } else {
                s = m + 1
            }
        }

        return if (e == -1) {
            s
        } else if (s == n) {
            e
        } else {
            s
        }
    }

    init {
        for (w in weights) {
            if (preSum.isEmpty()) {
                preSum.add(w.toDouble())
            } else {
                preSum.add(preSum.last() + w)
            }
        }
    }
}