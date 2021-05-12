<?php

namespace App\Repository;

use App\Entity\Fichefinance;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Fichefinance|null find($id, $lockMode = null, $lockVersion = null)
 * @method Fichefinance|null findOneBy(array $criteria, array $orderBy = null)
 * @method Fichefinance[]    findAll()
 * @method Fichefinance[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class FicheFinanceRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Fichefinance::class);
    }

    // /**
    //  * @return Fichefinance[] Returns an array of Fichefinance objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('f.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Fichefinance
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */



}
