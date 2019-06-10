/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCheatCautionDetailComponent } from 'app/entities/m-cheat-caution/m-cheat-caution-detail.component';
import { MCheatCaution } from 'app/shared/model/m-cheat-caution.model';

describe('Component Tests', () => {
  describe('MCheatCaution Management Detail Component', () => {
    let comp: MCheatCautionDetailComponent;
    let fixture: ComponentFixture<MCheatCautionDetailComponent>;
    const route = ({ data: of({ mCheatCaution: new MCheatCaution(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCheatCautionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCheatCautionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCheatCautionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCheatCaution).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
