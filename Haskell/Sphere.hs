-- File: Sphere.hs

-- Author: Christopher Camargo
-- CMPS 112: Winter 2014

-- Sphere module declaration.
module Sphere
( Sphere (Sphere)
, center
, radius
, diff
, ambient
, spec
, shine
, squaredRad
, isectBool
, getSphere
, getVal
, normal
, le
, tca
, d2
, thc
, intersect
) where

import Vec3
import Ray
import Data.List (find)

-- Sphere with center and radius. Also includes color data.
data Sphere = Sphere { center :: Vec3, radius :: Float, diff :: Vec3, ambient :: Vec3, spec :: Vec3, shine :: Float } deriving (Show, Eq)

squaredRad :: Sphere -> Float -- Returns the squared radius of a sphere.
squaredRad sphere = (radius sphere) * (radius sphere)

isectBool :: Int -> Int -> Sphere -> Bool -- Returns true if there is an intersection, false otherwise.
isectBool x y s = if (intersect (getRay x y) s) > 0.0 then True else False

getSphere :: Int -> Int -> [Sphere] -> Maybe Sphere -- Returns a sphere if it intersects a ray.
getSphere x y s = find (isectBool x y) s

getVal :: Int -> Int -> Sphere -> Float -- Returns the value of the rays parameter at an intersection.
getVal x y s = (intersect (getRay x y) s)

normal :: Sphere -> Vec3 -> Vec3 -- Returns the normal of a sphere at a point.
normal sphere point = normalize $ sub point (center sphere)

le :: Ray -> Sphere -> Vec3 -- Helper returns vector from ray origin to sphere center.
le r s = sub (center s) (origin r)

tca :: Ray -> Sphere -> Float -- Helper calculates distance from ray origin to sphere center.
tca r s = dot (le r s) (direction r)

d2 :: Ray -> Sphere -> Float -- Helper calculates distance from sphere center to ray origin.
d2 r s = dot (le r s) (le r s) - (tca r s) * (tca r s)

thc :: Ray -> Sphere -> Float -- Helper calculates distance from ray intersection point on sphere to center.
thc r s = sqrt (squaredRad s - (d2 r s))

intersect :: Ray -> Sphere -> Float -- Returns parameter t > 0 if ray intersects sphere.
intersect r s = if (tca r s) < 0 
				then 0.0 
				else if (d2 r s) > squaredRad s 
					 then 0.0 
					 else (tca r s) - (thc r s)