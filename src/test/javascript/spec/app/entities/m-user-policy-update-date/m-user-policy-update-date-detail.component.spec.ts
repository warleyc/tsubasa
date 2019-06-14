/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUserPolicyUpdateDateDetailComponent } from 'app/entities/m-user-policy-update-date/m-user-policy-update-date-detail.component';
import { MUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';

describe('Component Tests', () => {
  describe('MUserPolicyUpdateDate Management Detail Component', () => {
    let comp: MUserPolicyUpdateDateDetailComponent;
    let fixture: ComponentFixture<MUserPolicyUpdateDateDetailComponent>;
    const route = ({ data: of({ mUserPolicyUpdateDate: new MUserPolicyUpdateDate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUserPolicyUpdateDateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MUserPolicyUpdateDateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUserPolicyUpdateDateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mUserPolicyUpdateDate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
