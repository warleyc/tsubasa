/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionDetailComponent } from 'app/entities/m-gacha-rendition/m-gacha-rendition-detail.component';
import { MGachaRendition } from 'app/shared/model/m-gacha-rendition.model';

describe('Component Tests', () => {
  describe('MGachaRendition Management Detail Component', () => {
    let comp: MGachaRenditionDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionDetailComponent>;
    const route = ({ data: of({ mGachaRendition: new MGachaRendition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRendition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
