-- File: Ray.hs

-- Author: Christopher Camargo
-- CMPS 112: Winter 2014

-- Ray module declaration.
module Ray 
( Ray (Ray)
, origin
, direction
, getIntersection
, getx
, gety
, getRay
) where

import Vec3

-- Ray has an origin, direction, and t parameter.
data Ray = Ray { origin :: Vec3, direction :: Vec3 } deriving (Show)

getIntersection :: Ray -> Float -> Vec3 -- Returns a vector representing an intersection point.
getIntersection ray t = add (origin ray) (mult (direction ray) t)

getx :: Int -> Float -- Get the x coordinate of a pixel. Using 50 degree fov.
getx x = (2.0 * (((fromIntegral x :: Float) + 0.5) * (1.0/512.0)) - 1.0) * (tan (pi * 0.5 * 50.0 / 180.0))

gety :: Int -> Float -- Get the y coordinate of a pixel. Using 50 degree fov.
gety y = (1.0 - 2.0 * (((fromIntegral y :: Float) + 0.5) * (1.0/512.0))) * (tan (pi * 0.5 * 50.0 / 180.0))

getRay :: Int -> Int -> Ray -- Get a ray through the x and y pixel.
getRay x y = Ray (Vec3 0.0 0.0 0.0) ( normalize (Vec3 (getx x) (gety y) (-1.0)))