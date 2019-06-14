/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusIncentiveDetailComponent } from 'app/entities/m-login-bonus-incentive/m-login-bonus-incentive-detail.component';
import { MLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';

describe('Component Tests', () => {
  describe('MLoginBonusIncentive Management Detail Component', () => {
    let comp: MLoginBonusIncentiveDetailComponent;
    let fixture: ComponentFixture<MLoginBonusIncentiveDetailComponent>;
    const route = ({ data: of({ mLoginBonusIncentive: new MLoginBonusIncentive(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusIncentiveDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLoginBonusIncentiveDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLoginBonusIncentiveDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLoginBonusIncentive).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
