# Percolation
Estimation of the percolation threshold for an n*n grid via Monte Carlo Simulation  
Part of Algorithms - Part-1, Princeton University  

Percolation : Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the
composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through
to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

The model : We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.) 

The problem : In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates.  When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never percolates, and when p > p*, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived. Here, Monte Carlo Simulations have been made to estimate the value of p*.

Monte Carlo Simulation : Monte Carlo methods, or Monte Carlo experiments, are a broad class of computational algorithms that rely on repeated random sampling to obtain numerical results. The underlying concept is to use randomness to solve problems that might be deterministic in principle. They are often used in physical and mathematical problems and are most useful when it is difficult or impossible to use other approaches. Monte Carlo methods are mainly used in three problem classes : optimization, numerical integration, and generating draws from a probability distribution. 

The Experiment : 

Initialize all sites to be blocked.

Repeat the following until the system percolates:

     Choose a site uniformly at random among all blocked sites.

     Open the site.  
     
The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.  
     
To find if the system percolates we need to model the grid with the notion of "connectedness" and also be able to connect two sites.  
The Union Find data structure provides these functionalities efficiently through the union(for connecting two sites) and find(to find if the system percolates) methods.  

A source site is introduced, which is connected to all sites in the first row.  
A sink site is introduced, which is connected to all sites in the last row.  
If the source and sink are connected, then the system percolates.  

The experiment is repeated t times for an n* n grid and the value of p* is computed for each trial.  

Statistical measures computed :  
      Mean  
      Standard Deviation  
      Confidence Interval*  
      
* an estimate of a population parameter can either be a point measure(a single value as the estimate of the population parameter)  or an interval measure(a range in which the parameter is estimated to lie)  
  A confidence interval is an interval measure with an associated confidence level = the probability with which an interval will contain the true value of the parameter  
  eg. 90% of confidence intervals computed at a 90% confidence level contain the true value of the parameter
  
Here the 95% confidence interval is calculated.  

Percolation.java : To model the percolation system.  
PercolationStats : To perform the series of computational experiments.  

