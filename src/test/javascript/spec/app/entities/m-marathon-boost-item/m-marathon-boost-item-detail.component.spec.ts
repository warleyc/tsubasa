/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonBoostItemDetailComponent } from 'app/entities/m-marathon-boost-item/m-marathon-boost-item-detail.component';
import { MMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';

describe('Component Tests', () => {
  describe('MMarathonBoostItem Management Detail Component', () => {
    let comp: MMarathonBoostItemDetailComponent;
    let fixture: ComponentFixture<MMarathonBoostItemDetailComponent>;
    const route = ({ data: of({ mMarathonBoostItem: new MMarathonBoostItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonBoostItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonBoostItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonBoostItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonBoostItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
