/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamEffectBaseDetailComponent } from 'app/entities/m-team-effect-base/m-team-effect-base-detail.component';
import { MTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';

describe('Component Tests', () => {
  describe('MTeamEffectBase Management Detail Component', () => {
    let comp: MTeamEffectBaseDetailComponent;
    let fixture: ComponentFixture<MTeamEffectBaseDetailComponent>;
    const route = ({ data: of({ mTeamEffectBase: new MTeamEffectBase(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamEffectBaseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTeamEffectBaseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTeamEffectBaseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTeamEffectBase).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
