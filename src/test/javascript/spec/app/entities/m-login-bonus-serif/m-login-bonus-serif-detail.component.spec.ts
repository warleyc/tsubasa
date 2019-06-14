/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLoginBonusSerifDetailComponent } from 'app/entities/m-login-bonus-serif/m-login-bonus-serif-detail.component';
import { MLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';

describe('Component Tests', () => {
  describe('MLoginBonusSerif Management Detail Component', () => {
    let comp: MLoginBonusSerifDetailComponent;
    let fixture: ComponentFixture<MLoginBonusSerifDetailComponent>;
    const route = ({ data: of({ mLoginBonusSerif: new MLoginBonusSerif(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLoginBonusSerifDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLoginBonusSerifDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLoginBonusSerifDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLoginBonusSerif).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
