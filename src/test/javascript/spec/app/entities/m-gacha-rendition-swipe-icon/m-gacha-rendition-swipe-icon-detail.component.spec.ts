/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionSwipeIconDetailComponent } from 'app/entities/m-gacha-rendition-swipe-icon/m-gacha-rendition-swipe-icon-detail.component';
import { MGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';

describe('Component Tests', () => {
  describe('MGachaRenditionSwipeIcon Management Detail Component', () => {
    let comp: MGachaRenditionSwipeIconDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionSwipeIconDetailComponent>;
    const route = ({ data: of({ mGachaRenditionSwipeIcon: new MGachaRenditionSwipeIcon(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionSwipeIconDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionSwipeIconDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionSwipeIconDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionSwipeIcon).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
