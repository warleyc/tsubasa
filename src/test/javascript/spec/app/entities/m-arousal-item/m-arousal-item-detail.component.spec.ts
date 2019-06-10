/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MArousalItemDetailComponent } from 'app/entities/m-arousal-item/m-arousal-item-detail.component';
import { MArousalItem } from 'app/shared/model/m-arousal-item.model';

describe('Component Tests', () => {
  describe('MArousalItem Management Detail Component', () => {
    let comp: MArousalItemDetailComponent;
    let fixture: ComponentFixture<MArousalItemDetailComponent>;
    const route = ({ data: of({ mArousalItem: new MArousalItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MArousalItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MArousalItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MArousalItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mArousalItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
