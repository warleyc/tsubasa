/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardRarityDetailComponent } from 'app/entities/m-card-rarity/m-card-rarity-detail.component';
import { MCardRarity } from 'app/shared/model/m-card-rarity.model';

describe('Component Tests', () => {
  describe('MCardRarity Management Detail Component', () => {
    let comp: MCardRarityDetailComponent;
    let fixture: ComponentFixture<MCardRarityDetailComponent>;
    const route = ({ data: of({ mCardRarity: new MCardRarity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardRarityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCardRarityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardRarityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCardRarity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
