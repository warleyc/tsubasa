/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamEffectRarityDetailComponent } from 'app/entities/m-team-effect-rarity/m-team-effect-rarity-detail.component';
import { MTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';

describe('Component Tests', () => {
  describe('MTeamEffectRarity Management Detail Component', () => {
    let comp: MTeamEffectRarityDetailComponent;
    let fixture: ComponentFixture<MTeamEffectRarityDetailComponent>;
    const route = ({ data: of({ mTeamEffectRarity: new MTeamEffectRarity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamEffectRarityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTeamEffectRarityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTeamEffectRarityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTeamEffectRarity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
