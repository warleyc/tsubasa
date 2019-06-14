/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamEffectLevelDetailComponent } from 'app/entities/m-team-effect-level/m-team-effect-level-detail.component';
import { MTeamEffectLevel } from 'app/shared/model/m-team-effect-level.model';

describe('Component Tests', () => {
  describe('MTeamEffectLevel Management Detail Component', () => {
    let comp: MTeamEffectLevelDetailComponent;
    let fixture: ComponentFixture<MTeamEffectLevelDetailComponent>;
    const route = ({ data: of({ mTeamEffectLevel: new MTeamEffectLevel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamEffectLevelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTeamEffectLevelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTeamEffectLevelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTeamEffectLevel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
