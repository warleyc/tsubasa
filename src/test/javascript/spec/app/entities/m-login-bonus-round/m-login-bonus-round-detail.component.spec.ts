/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusRoundDetailComponent } from 'app/entities/m-login-bonus-round/m-login-bonus-round-detail.component';
import { MLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';

describe('Component Tests', () => {
  describe('MLoginBonusRound Management Detail Component', () => {
    let comp: MLoginBonusRoundDetailComponent;
    let fixture: ComponentFixture<MLoginBonusRoundDetailComponent>;
    const route = ({ data: of({ mLoginBonusRound: new MLoginBonusRound(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusRoundDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLoginBonusRoundDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLoginBonusRoundDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLoginBonusRound).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
