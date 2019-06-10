/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionRarityDetailComponent } from 'app/entities/m-action-rarity/m-action-rarity-detail.component';
import { MActionRarity } from 'app/shared/model/m-action-rarity.model';

describe('Component Tests', () => {
  describe('MActionRarity Management Detail Component', () => {
    let comp: MActionRarityDetailComponent;
    let fixture: ComponentFixture<MActionRarityDetailComponent>;
    const route = ({ data: of({ mActionRarity: new MActionRarity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionRarityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MActionRarityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionRarityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mActionRarity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
