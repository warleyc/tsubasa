/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueEffectDetailComponent } from 'app/entities/m-league-effect/m-league-effect-detail.component';
import { MLeagueEffect } from 'app/shared/model/m-league-effect.model';

describe('Component Tests', () => {
  describe('MLeagueEffect Management Detail Component', () => {
    let comp: MLeagueEffectDetailComponent;
    let fixture: ComponentFixture<MLeagueEffectDetailComponent>;
    const route = ({ data: of({ mLeagueEffect: new MLeagueEffect(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueEffectDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLeagueEffectDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueEffectDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLeagueEffect).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
