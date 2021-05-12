<?php

namespace App\Controller;

use App\Entity\Depense;
use App\Repository\DepenseRepository;
use App\Repository\UserRepository;
use Knp\Snappy\Pdf;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class StudentDepenseController extends AbstractController
{
    /**
     * @Route("/student/depense", name="student_depense")
     */
    public function index(DepenseRepository $depenseRepository): Response
    {
        $depenses = $depenseRepository->findBy(array('idutilisateur'=> '12345661'));
        return $this->render('student_depense/index.html.twig', [
            'depenses' => $depenses
        ]);
    }


    /**
     * @Route("/student/recu/{id}/", name="student_recu" ,methods={"GET","POST"})
     */
    public function pdf_recu(Request $request, Depense $depense , DepenseRepository $depenseRepository
    , UserRepository $userRepository
    ): Response
    {

        $knpSnappyPdf = new Pdf('D:\wkhtmltopdf_32\bin\wkhtmltopdf.exe');

        $depense = $depenseRepository->find($depense->getId());
        $usr = $userRepository->find($depense->getIdutilisateur());
        $html = $this->render('base/pdfRecipe.html.twig', [
            'depense' => $depense , 'user' => $usr
        ]);
      //  $knpSnappyPdf->getOutputFromHtml( $html);
        header('Content-Type: application/pdf');
        echo $knpSnappyPdf->getOutputFromHtml($html);

        return $this->render('student_depense/index.html.twig', [
            'depenses' => $depenses
        ]);
    }





}


