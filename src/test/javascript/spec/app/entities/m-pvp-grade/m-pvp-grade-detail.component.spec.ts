/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpGradeDetailComponent } from 'app/entities/m-pvp-grade/m-pvp-grade-detail.component';
import { MPvpGrade } from 'app/shared/model/m-pvp-grade.model';

describe('Component Tests', () => {
  describe('MPvpGrade Management Detail Component', () => {
    let comp: MPvpGradeDetailComponent;
    let fixture: ComponentFixture<MPvpGradeDetailComponent>;
    const route = ({ data: of({ mPvpGrade: new MPvpGrade(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpGradeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpGradeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpGradeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpGrade).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
