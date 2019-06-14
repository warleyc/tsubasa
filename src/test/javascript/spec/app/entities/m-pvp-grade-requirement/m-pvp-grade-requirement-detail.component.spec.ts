/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpGradeRequirementDetailComponent } from 'app/entities/m-pvp-grade-requirement/m-pvp-grade-requirement-detail.component';
import { MPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';

describe('Component Tests', () => {
  describe('MPvpGradeRequirement Management Detail Component', () => {
    let comp: MPvpGradeRequirementDetailComponent;
    let fixture: ComponentFixture<MPvpGradeRequirementDetailComponent>;
    const route = ({ data: of({ mPvpGradeRequirement: new MPvpGradeRequirement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpGradeRequirementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpGradeRequirementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpGradeRequirementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpGradeRequirement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
