/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDeckRarityConditionDescriptionDetailComponent } from 'app/entities/m-deck-rarity-condition-description/m-deck-rarity-condition-description-detail.component';
import { MDeckRarityConditionDescription } from 'app/shared/model/m-deck-rarity-condition-description.model';

describe('Component Tests', () => {
  describe('MDeckRarityConditionDescription Management Detail Component', () => {
    let comp: MDeckRarityConditionDescriptionDetailComponent;
    let fixture: ComponentFixture<MDeckRarityConditionDescriptionDetailComponent>;
    const route = ({ data: of({ mDeckRarityConditionDescription: new MDeckRarityConditionDescription(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDeckRarityConditionDescriptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDeckRarityConditionDescriptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDeckRarityConditionDescriptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDeckRarityConditionDescription).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
